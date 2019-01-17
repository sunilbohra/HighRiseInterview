package com.example.sbohr002;

import com.example.sbohr002.myapplication.com.example.sbohr002.myapplication.data.MoviesController;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MovieControllerUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void movieController_get_movies_count(){
        MoviesController mc =  new MoviesController();
        assertEquals(mc.getMovies().size(), 10);
    }

}