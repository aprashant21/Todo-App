# TODO_APP


<h2> THINGS COVERED IN THE PROJECT</h2>
SINGLE PAGE APPLICATION, MENUS, IMPLICT INTENT, FRAGMENTS, ROOM DATABASE(SQLITE), LIFECYCLE AND STATE MANAGED, CRUD OPERATION, RECYLCERVIEW, MVVM ARCHITECTURE, OBSERVE PATTERN ETC            

<h3> INTRODUCTION </h3>
This is a Single Page Application. There is only one mainActivity and two fragment i.e. TODOLIST fragment and TODOADD fragment. Fragment is attached in center of the screen using Frame layout and it is replaced by the Fragment manager and only center of the screen changes. 

At First, TODOLIST fragment is added in the middle by Fragmentmanager and then after clicking on the add button below it is replaced.

There is no task at first and only gif is displaying as showing in the below images:

<img src="images/no_data.jpg" alt="no_data_image" height="400px" width="200px" />     <img src="images/no_data.gif" alt="no_data_image" height="400px" width="200px" /> 

After clicking in the plus button, Frame layout is replaced using Fragment manager and we can see the only center view is changed and we are in the TODOADD fragment. 

Task can be added in the following ways as shown in image below:

<img src="images/add_task.jpg" alt="ADD TASK IMAGE" height="400px" width="200px" />     <img src="images/add_task.gif" alt="ADD TASK GIF" height="400px" width="200px" />

After added the task, Frame layout back stack from the and center view is replace and we are in the main TODOLIST fragment and gif image is removed. 

There is Menu Items in the APPBAR that are:

* ABOUT,
* CONTACT

There is two items in the menu and After clicking in those two item, Dialog box is opened as shown in the image below:

<img src="images/about.jpg" alt="about" height="400px" width="200px" />    

In above image, versionName is the programmatically setted using BuildConfig class. 

You can see that contact information is in the contact dialog box and there are two buttons "Call Button" and "Email Button".

"Call" and "Email" are the two imageButton, "CALL" button directly open the DIAL Screen and "EMAIL" button used IMPLICIT INTENT as shown in below:

<img src="images/contact_ss.jpg" alt="contact" height="400px" width="200px" /> 

IMPLICT INTENT in the form of emailing in the email as shown in below:

<img src="images/images.jpg" alt="implicit" height="400px" width="200px" /> 


After adding task, todo list is full of task and if we want to delete some task, we can click in the radio button upper left side. After checking the radio button, a Dialog box is opened. There are two option in the dialog box "COMPLTED" and "CANCEL".

<img src="images/complete_delete_task.jpg" alt="delete" height="400px" width="200px" />     <img src="images/update_task.jpg" alt="update" height="400px" width="200px" />

After clicking on "COMPLETED" button, dialog box is closed and task is deleted. 
After clicking on "CANCEL" button, dialog box is dismissed;





  

