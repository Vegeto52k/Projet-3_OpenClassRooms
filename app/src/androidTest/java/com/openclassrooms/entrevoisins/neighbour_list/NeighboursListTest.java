
package com.openclassrooms.entrevoisins.neighbour_list;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.AtPosition.atPosition;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.ProfilView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int ITEMS_FAVORITES_COUNT = 3;

    private ListNeighbourActivity mActivity;


    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * When we click on item, the profilActivity's item is displayed
     */
    @Test
    public void myNeighbourList_item_openProfileActivity() {
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new ProfilView()));
        onView(withId(R.id.profile_activity)).check(matches(isDisplayed()));
    }

    /**
     * Same name in profilActivity and myNeighbourList
     */
    @Test
    public void profilActivity_contains_sameName() {
        onView(withId(R.id.list_neighbours)).check(matches(atPosition(1, hasDescendant(withText("Jack")))));
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new ProfilView()));
        onView(withId(R.id.profile_name)).check(matches(withText("Jack")));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void favoriteFragment_deleteAction_shouldRemoveItem() {
        onView(withId(R.id.list_neighbours)).perform(swipeLeft());
        onView(withId(R.id.list_neighbours_favorites)).check(withItemCount(ITEMS_FAVORITES_COUNT));
        onView(withId(R.id.list_neighbours_favorites)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(withId(R.id.list_neighbours_favorites)).check(withItemCount(ITEMS_FAVORITES_COUNT - 1));
    }

    /**
     * Only favorite neighbour in favoriteList
     */
    @Test
    public void favoriteFragment_contains_onlyFavoritesNeighbours() {
        onView(withId(R.id.list_neighbours)).perform(swipeLeft());
        onView(withId(R.id.list_neighbours_favorites)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new ProfilView()));
        onView(withId(R.id.profile_favorite_on)).check(matches(isDisplayed()));
        onView(withId(R.id.profile_back)).perform(click());
        onView(withId(R.id.list_neighbours_favorites)).perform(RecyclerViewActions.actionOnItemAtPosition(1, new ProfilView()));
        onView(withId(R.id.profile_favorite_on)).check(matches(isDisplayed()));
        onView(withId(R.id.profile_back)).perform(click());
        onView(withId(R.id.list_neighbours_favorites)).perform(RecyclerViewActions.actionOnItemAtPosition(2, new ProfilView()));
        onView(withId(R.id.profile_favorite_on)).check(matches(isDisplayed()));
    }


}