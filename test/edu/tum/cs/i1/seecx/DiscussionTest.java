package edu.tum.cs.i1.seecx;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.*;

import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(EasyMockRunner.class)
public class DiscussionTest {

	@TestSubject
	private Discussion discussion = new Discussion();

	@Mock
	private Course courseMock;
	
	@Mock
	private Comment commentMock;

	@Test
	public void testComment() {
		
		int initialSize = discussion.getNumberOfComments();
			
		expect(commentMock.save()).andReturn(true);
		replay(commentMock);
		discussion.addComment(commentMock);
		assertEquals(initialSize + 1, discussion.getNumberOfComments());
	}
	
	@Test
	public void testCommentIfSavingFails() {
		
		int initialSize = discussion.getNumberOfComments();
			
		expect(commentMock.save()).andReturn(false);
		replay(commentMock);
		discussion.addComment(commentMock);
		assertEquals(initialSize, discussion.getNumberOfComments());
	}	

	@Test
	public void testStartCourseDiscussion() {
		
		Student student = new Student("Don","Trump","1950","MIS","MKT");

		expect(courseMock.isDiscussionAllowed(student)).andReturn(true);
		replay(courseMock);
		assertTrue(discussion.startCourseDiscussion(courseMock,student,"Software Engineering"));
	}		
	
}
