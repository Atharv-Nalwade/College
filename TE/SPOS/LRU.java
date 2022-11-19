// Let capacity be the number of pages that
// memory can hold.  Let set be the current
// set of pages in memory.

// 1- Start traversing the pages.
//  i) If set holds less pages than capacity.
//    a) Insert page into the set one by one until 
//       the size  of set reaches capacity or all
//       page requests are processed.
//    b) Simultaneously maintain the recent occurred
//       index of each page in a map called indexes.
//    c) Increment page fault
//  ii) Else 
//    If current page is present in set, do nothing.
//    Else 
//      a) Find the page in the set that was least 
//      recently used. We find it using index array.
//      We basically need to replace the page with
//      minimum index.
//      b) Replace the found page with current page.
//      c) Increment page faults.
//      d) Update index of current page.

// 2. Return page faults.

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

// the page not utilized for the longest time in the memory (compared to all other pages) gets replaced.

class Main
{
	static int pageFaults(int pages[], int n, int capacity)
	{
	
		HashSet<Integer> s = new HashSet<>(capacity); // To represent set of current pages. We use  an unordered_set so that we quickly check if a page is present in set or not
		HashMap<Integer, Integer> indexes = new HashMap<>(); // To store least recently used indexe of pages.
	
		int page_faults = 0;
		for (int i=0; i<n; i++)
		{
			if (s.size() < capacity) //// Check if the set can hold more pages
			{	
				if (!s.contains(pages[i]))      // Insert it into set if not present already which represents page fault
				{
					s.add(pages[i]);
					page_faults++; // incr page_faults
				}
				indexes.put(pages[i], i); // Store the recently used index of each page
			}
			else // if set cannot hold more pages  If the set is full then need to perform lru i.e. remove the least recently used page and insert the current page
			{
				if (!s.contains(pages[i]))
				{
				     // Find the least recently used pages that is present in the set
					int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE;
					Iterator<Integer> itr = s.iterator();
					while (itr.hasNext()) {
						int temp = itr.next();
						if (indexes.get(temp) < lru)
						{
							lru = indexes.get(temp);
							val = temp;
						}
					}
					s.remove(val);      // Remove the indexes page
				    indexes.remove(val);   //remove lru from hashmap
					s.add(pages[i]); // insert the current page
					page_faults++;
				}
				indexes.put(pages[i], i);  // Update the current page index
			}
		}
		return page_faults;
	}
	

	public static void main(String args[])
	{
		int pages[] = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2};
		
		int capacity = 4;
		
		System.out.println(pageFaults(pages, pages.length, capacity));
	}
}

