package edu.chapman.cpsc356.routegenerator.SQL_old;//package com.example.tyler.finalproject.SQL;
//
//import android.arch.persistence.room.Room;
//import android.content.Context;
//
//import com.example.tyler.finalproject.SQL.UserDao;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.io.IOException;
//import java.util.List;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//@RunWith(AndroidJUnit4.class)
//public class SimpleEntityReadWriteTest {
//    private UserDao mUserDao;
//    private TestDatabase mDb;
//
//    @Before
//    public void createDb() {
//        Context context = InstrumentationRegistry.getTargetContext();
//        mDb = Room.inMemoryDatabaseBuilder(context, TestDatabase.class).build();
//        mUserDao = mDb.getUserDao();
//    }
//
//    @After
//    public void closeDb() throws IOException {
//        mDb.close();
//    }
//
//    @Test
//    public void writeUserAndReadInList() throws Exception {
//        User user = TestUtil.createUser(3);
//        user.setName("george");
//        mUserDao.insert(user);
//        List<User> byName = mUserDao.findUsersByName("george");
//        assertThat(byName.get(0), equalTo(user));
//    }
//}
