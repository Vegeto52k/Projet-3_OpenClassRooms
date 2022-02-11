package com.openclassrooms.entrevoisins.service;


import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }


    // favorite
    @Override
    public List<Neighbour> getFavorites() {
        ArrayList favorites = new ArrayList();
        for (Neighbour neighbour : neighbours) {
            if (neighbour.isFavorite() == true) {
                favorites.add(neighbour);
            }
        }
        return favorites;
    }

    @Override
    public void setFavorite(Neighbour neighbour) {
        for (Neighbour neighbour1 : neighbours) {
            if (neighbour.equals(neighbour1)) {
                neighbour1.setFavorite(neighbour.isFavorite());
            }
        }
    }


}
