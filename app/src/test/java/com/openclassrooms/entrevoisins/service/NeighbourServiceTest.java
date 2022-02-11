package com.openclassrooms.entrevoisins.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void addFavorite() {
        List<Neighbour> listNeighnour = service.getFavorites();
        int favoriteNumber = listNeighnour.size();
        Neighbour mNeighbour = service.getNeighbours().get(0);
        mNeighbour.setFavorite(true);
        service.setFavorite(mNeighbour);
        listNeighnour = service.getFavorites();
        int newFavoriteNumber = listNeighnour.size();
        assertEquals(favoriteNumber+1, newFavoriteNumber);
    }

   @Test
   public void removeFavorite() {
       List<Neighbour> listNeighnour = service.getFavorites();
       int favoriteNumber = listNeighnour.size();
       Neighbour mNeighbour = service.getNeighbours().get(1);
       mNeighbour.setFavorite(false);
       service.setFavorite(mNeighbour);
       listNeighnour = service.getFavorites();
       int newFavoriteNumber = listNeighnour.size();
       assertEquals(favoriteNumber-1, newFavoriteNumber);
   }
}
