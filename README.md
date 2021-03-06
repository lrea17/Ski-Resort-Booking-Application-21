# My Personal Project

## Ski Resort Booking System

Proposal:

- **What will the application do?**  
  The application will be a system for employees of a ski resort. In this phase, the application will allow employees to
  create a new guest and book a reservation for that same guest by adding a pass to their account. Employees will also
  be able to look up existing guests by their unique account id number to make a reservation for that existing guest.
  Similarly, they will be able to look up a guest by their account id to cancel an existing reservation, which will
  unexpired their last used pass and make it available for the next time they would like to book. The users will be able
  to lookup an existing guest and view the number of days skied by viewing the number of expired passes they have.
  Lastly, the users will be able to look up existing guests by their account id and permanently delete them from the
  system. All guests created will have a unique account id which is used in multiple instances to make changes to the
  guest's account. At this point, adding a pass to the guest account under their list of expired passes is “the
  reservation”. An expired pass is the equivalent to a day skied. A guest must have a current unexpired pass on their
  account to make a reservation. If they do not have one, a new one will be created and used to make the reservation.


- **Assumptions**
  Right now, the application does what I have described above. I am assuming that the employees will always be inputting
  the correct characters required into the input fields. I recognize in a real-life scenario, this may not be true but
  for this phase of my project this holds. I would like to make changes in the future to handle problems of human error
  or other mistyping that could “break” the system.


- **Who will use it?**  
  Employees of the ski resort will be using this application as a system to manage the ski resort guest accounts and
  reservations for the mountain.


- **Why is this project of interest to you?**
  My inspiration for this project is the less than great experience I've had using Whistler Mountain’s online booking
  system during Covid this year. While I can only hope to build something better than what they currently have in place,
  it’s something I'm familiar with and would be happy to create some improvements. I think this project is also
  something that could be replicated and tweaked for other business models not just specifically for a ski resort.


- **Future Plans for the Application**  
  In the future, I would like to expand the functionality of the application. This would include expanding the search
  functionality beyond just the guest account id to include their name and perhaps a unique “code word” in case one does
  not know their account id. Additionally, I would like to make a reservation class to separate reservations from being
  linked to adding passes to the guest account. In creating reservations, I have hope to include dates in this
  application so it is possible to reserve a specific day and also to be able to see what day a pass is for. Lastly, I
  would like to expand my pass class to include different types of passes (child, youth, adult, senior), as well as
  having passes with a specific number of days that they are usable for before expiring. As I continue building this
  application, I will keep my options open to further expand beyond these additional capabilities I hope to build.

## User Stories

Phase 1:

- As a user, I want to be able to create a new guest and make a reservation for them
- As a user, I want to be able to see the used passes on a guests account
- As a user, I want to be able to lookup an existing guest by their account id and add a reservation for a ski day
- As a user, I want to be able to lookup an existing guest by their account id and cancel a reservation for a ski day
- As a user, I want to be able to permanently delete a guest from the list of guests

Phase 2: 
- As a user, when I start the application, I want to be given the option to load my most recent changes from file.
- As a user, when I select the quit option from the application menu, I want to be reminded to save my changes to 
  file and have the option to do so or not.
  
Phase 4: Task 2:

- I have a pre-existing type hierarchy; ApplicationButtons is an abstract class and all my "buttons" in
  my GUI extend this class to implement/override the methods addListener() and createButtons(). For example, Save,
  NewGuest, Load and LookupGuest all extend ApplicationButtons and override the methods mentioned above.  Each button
  is unique and performs a different action so each subclass overrides those methods differently.  

Phase 4: Task 3:

If I had more time I would definitely do some refactoring to improve this project! These are the changes I would make:

- add a "Reservation" class to the ski model - this would make booking a "reservation" separate from adding a pass to 
  the guests account.  Right now, a booking/canceling a "reservation" is tied to adding/removing passes from a guest 
  profile/
  
- You can't really see it in my UML, but I somewhat reuse the MainMenu button on a few different panels within the GUI.
  I would like to have somehow added the main menu button as maybe a method within the ApplicationButtons class so that 
  methods implementing ApplicationButtons could override that method and there would be less repetitive code/
  
- I don't know if its possible, but it would be nice to have fewer classes in the UI package, it seems like there's a
  lot going on in there and maybe some repetition with code.  Maybe by trying to remove any duplication a little better.
  
- I would make my classes in the model package robust. I tried to fix up the Guest class for phase 4 but it caused for
  some code coverage issues that did not appear before so I decided to leave the code as is.



