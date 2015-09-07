package com.project.crawler;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Daniel Steger on 06.09.15.
 */
public class DataParserImplTest {

    @Test
    public void testParseChannels() throws Exception {

    }

    @Test
    public void testParseStreams() throws Exception {
        String s = "2015-09-07T05:29:43Z";
        String s1 = s.substring(0, 10);
        String s2 = s.substring(11, 19);

        System.out.println(s1 + " " + s2);
    }

    @Test
    public void testParseGames() throws Exception {
        DataParserImpl parser;
    }
}