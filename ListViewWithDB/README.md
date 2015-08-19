In ListViewAndSQLite

I used SimpleCursorAdapter the DetailsDBAdapter was created with an intention to work on the basis of cursor location and
DetailsDBAdapter used to return an cursor.

In ListViewWithBaseAdapter

I used Custom Adapter(PersonListAdapter) which extends BaseAdapter and itt expects a List instead of cursor Location so,
I had to change the logic in DetailsDBAdapter to return me a Details List.

While the use of clear button in DetailsActivity

When we reset the whole activity we have to take care about back-button Logic(Not worked on it)
