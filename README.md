RMIT University VietnamCourse: INTE2512 Object-Oriented Programming
Semester: 2020B
Assessment name: Library Management System 
Name: Nguyen Hoang Khang
id: s3802040


1. INTRODUCTION:

This software is about implementing a text-based Java program, QuickLib, to manage the library’s collection of items (books, journals, and DVDs) and lend them to registered members.
The librarian will be able to use this software to load and save data. In addition, this program can also do some functions for the user such as search the item , search the member and let the member return and borrow the items.

2. FEATURES

_ Search items by keywords
_ Add new item
_ Update item info
_ Search members by keywords
_ Register the new member
_ Update member info
_ Borrow items
_ Return items
_ Save data
_ Quit

3. DESIGN

I created an abstract class Item for the other classes to extend this class and to optimise the code in adding and updating the whatever the item is.
I have included some checked functions to check the unique of the isbn , isnn and the member id.
This software also checks for the format of mail (i used @rmit.edu.vn in my java) , phone, and the format of date.
This program should produce no error during running time since I tried to catch many possible errors and tested many times.
Besides, I also have time for the user to read the message. If the user finishes reading the message, press enter to quit the method.
Another thing to know that, whenever the member borrows an item, the issued date will be automatically updated with the current date of the system. Only when the member returns the item, it is needed to enter the returned date.
Lastly, when checking the ISBN, it is needed to use long data type instead of int since the range of integer is not sufficient for storage 13 digits number.

4. INSTALLATION

This is the user-friendly interface which is quite feasible to use and get used to. The user will not get confused when entering the wrong format because it will have the instructions or format to follow
Note that, this software always need to press enter when you want to finish the method or want to get back to the menu.

5. KNOWN BUGS
None

6. ACKNOWLEDGEMENT
None
