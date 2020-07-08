package com.jwhh.notekeeper;

import android.service.autofill.UserData;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.pressBack;
import static org.hamcrest.Matchers.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;

@RunWith(AndroidJUnit4.class)
public class NoteCreationTest {
    static DataManager sDataManager;

    @BeforeClass
    public static void classSetup() {
        sDataManager = DataManager.getInstance();
    }

    @Rule
    public ActivityTestRule<NoteListActivity> mNoteListActivityActivityTestRule = new ActivityTestRule<>(NoteListActivity.class);

    @Test
    public void createNewNote() throws InterruptedException {
        CourseInfo course = sDataManager.getCourse("java_lang");
        String noteTitle = "Test Title";
        String noteText = "Automated UI Test";

        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.spinner_courses)).perform((click()));
        onData(allOf(instanceOf(CourseInfo.class), equalTo(course))).perform(click());

        onView(withId(R.id.spinner_courses)).check(matches(withSpinnerText(containsString(course.getTitle()))));

        onView(withId(R.id.text_note_title)).perform(typeText(noteTitle))
                .check(matches(withText(containsString(noteTitle))));

        onView(withId(R.id.text_note_text)).perform(typeText(noteText), closeSoftKeyboard());

        onView(withId(R.id.text_note_text))
                .check(matches(withText(containsString(noteText))));

        pressBack();

        int noteIndex = sDataManager.getNotes().size() - 1;
        NoteInfo latestNote = sDataManager.getNotes().get(noteIndex);

        Assert.assertEquals(course, latestNote.getCourse());
        Assert.assertEquals(noteText, latestNote.getText());
        Assert.assertEquals(noteTitle, latestNote.getTitle());


    }
}