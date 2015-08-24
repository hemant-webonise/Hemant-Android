
Assignment-

1. In the last Assignment you guys have used ListView and Add/Details screen as Activity.
2. Same assignment but use fragments.
3. While navigating from one fragment to other, the animation should be as follows.
4. Current Fragment should go out from left of the screen and new fragment will slide in from right.
5. Similarly, on press of back button current fragment should slide out from right and previous fragment should come from left.

Working on the Scenario-

1. Initially we had two Activites which were suppose to changed into Fragments.
2. So now we have one Activity and Two Fragments - HomeScreenFragment and SaveScreenFragment and then all the other files are not changed except the ListAdapter.
3. Fragments and Acvities behave differently to findViewById(); which wud be clear in comparision of both codes(This and LISTVIEWFINAL).

Working on the backward Compactibility.

1. Changed the import for Fragments used.
2. And so similarly changed the imports of FragmentTransaction,FragmentManager
3. Chage the extend of main class  to FragmentActivity and import - 

   1. import android.support.v4.app.FragmentActivity;
   2. import android.support.v4.app.FragmentManager;
   3. import android.support.v4.app.FragmentTransaction;
   
4. Make use of getSupportFragmentManager() method.



