# Tip-Calculator
An android application to calculate tip and share per person for a given amount. This application was created as a part of the course CSCI 5708 Mobile Computing

## Introduction
Tip Calculator application will help the user to calculate the tip on the bill amount. The application is simple to use and requires no guidance or help.

![](/images/image_1.png)

## Unified Modeling Language

In this application, the user needs to input an amount, input the tip percentage and input the party size (optional). The application will calculate the tip amount, the total amount and share per head and show the output to the user on the screen.

![](/images/image_2.png)

## Tools

Below are the tools used to develop the application.

- Android Studio [1]
	Design Prototype and Application Development were done using Android Studio

- Draw.IO [2]
	UML Diagram and Wireframe creation was done using the tool Draw.IO	

- Android Device Vivo V9
	The application was installed on tested on the device [This is my personal device]

## Implementation

The implementation for the application was carried out in phases. The phases are Wireframing, design prototype, finalizing the design and developing the application.  
### Wireframe

Wireframing of the application helps to understand the initial design of the application. Using the wireframe, the design components and the application programming component can be figured out.

![](/images/image_3.png)

### Design Prototype

The high-fidelity prototype was created in Android Studio using XML. The prototype helps to understand the alignment for each section, the color scheme to be used, the size of each section, etc.

![](/images/image_4.png)

### Final Design

In the final design, the accurate color schemes were chosen, the shape of the buttons and text views were changed, the font size was increased and the text colours were changed.

![](/images/image_1.png)

### Development

The application programming for the application is done using Kotlin [3].

In this application, an input area is provided where the user can enter the amount of the bill. An option for tip has been provided which has minimum value as 0 and the maximum value is 25. This is controlled using the seek bar. Another option to split the bill is provided. The user can decrement the value using the minus button and increment the value using the plus button. Default values for these three fields are set. The amount will be $ 0.0, the tip will be 15% and the party size is set to an initial value of 1.

In the next section, the user can view the result for these inputs. There are three text views provided where users can see the tip amount payable along with the bill amount. The addition of tip amount and bill amount can be seen in the next field, Total. If the user has company and has entered a value for party size, the per head amount can be seen in the next field, Split.

The next section has the keypad with 0-9 digits button, the button which appends two zeros, a clear button, and a reset button. There is no need for a calculate button because, as and when the user input the amount, change the tip value and change the party size value, the output fields will be updated automatically.

To let the user know that they have pressed a certain button, the system will provide Haptic Feedback [4] to the user. This haptic feedback is provided by a vibration sensation. (This functionality can be tested by running the application on a device).

The user can enter the amount by clicking on the button from 0-9 and the button for entering two zeros. To remove the incorrect value, the user can click the clear button “C”. To reset the entire input, the user can click the reset button, which will bring all the values to the default value. To increase or decrease the number of people, the user can click the increment (+) or decrement (-) button. All this is handled using the on click listener.

To display the user regarding the percentage of tip they have entered, there is a text view that shows the current progress of the seek bar. This change of the value and reflecting it to the output is handled using the Seek Bar Change Listener [5].

Toast [6] functionality is used to provide the error message if the user entered characters greater than 9 in the input text area.

Below are the views use to design the layout

| Field | Usage |
| ------ | ------ |
| Buttons | Increment/Decrement, Clear/Reset, Digit 0 to 9/ Double zero |
| Text View | Amount, Tip Amount, Total Amount, Per head Amount |
| Seek Bar | Tip percentage |
| Label | Split, Tip, Total & Share |

## Nielsen’s Usability Heuristics

While developing the application (designing and programming), I tried to cover some of Nielsen’s Usability Heuristics [7]. Below are the evidences that they are followed in the application.

### Visibility of System Status
The user can see the output of the calculations as soon as they change any input. This is always available on the screen. Also, once the user changes the value on seek bar it is automatically updated in the text field near it to let the user know the percent they are tipping.

![](/images/image_5.png)

### User Control and Freedom

If the user enters a wrong value or wants to remove the entire input, the application provided them with a clear and reset button. The clear button will remove a single character from the input and the reset button will reset the entire input to the default values.

![](/images/image_6.png)

### Help users to recognize, Diagnose, and Recover from errors

There can be a maximum 9 characters (including the decimal) in the amount input area. After this user won’t be allowed to enter any more digit and a Toast with message “Entered amount is too large!!” will be displayed. This is done to avoid the error if the number limit is reached for a particular data type.
(This idea was suggested by Prof. Meredith)	

![](/images/image_7.png)

### Prevent Errors

There are two pieces of evidence for this heuristic.

1.	As we are working with monetary values, a mistake in entering the amount may yield incorrect results. For example, 1250.00 instead of 12.50. To avoid this scenario, I have not provided an option for the user to enter decimal points themselves. The user will enter the value starting at the cent’s place and once they enter more value, it will shift to the dollar’s place.
	For example: Enter $ 12.50

    | Field | Usage |
    | ------ | ------ |
    | input value “1” |	$ 0.01 |
    | input value “2” | $ 0.12 |
    | input value “5” |	$ 1.25 |
    | input value “0” | $ 12.50 |

2.	The increment and decrement value for the number of people cannot go below 1. A condition is applied in the programming to prevent this error. As any number below one will yield invalid output. If the number becomes zero, the exception will be raised that division will zero is not possible and if the number becomes negative, the per head price will be negative which is incorrect.

So, this is how I have prevented the errors from occurring in the application.

### Provide Flexibility and Efficiency of Use

To allow user to enter a big number with many zeros, the button “00” will append two zeros together. This is done to provide ease to the user.

![](/images/image_8.png)

### Focus on Aesthetic and Minimalist Design

To provide the user with a good user interface, only the required sections are inserted. They are properly aligned so that the user does not get confused. The input bar has a black color, which can be noticed as soon as the application is opened. This helps the user understand that the value is to be inserted in that field. For tip percent and the number of people, a different type of input style is being used so that the user does not get confused while they are inserting values using the custom keypad. They can see the update in the output as soon as they change the input, i.e., they don’t need to click on the button such as “calculate” to see the output.

![](/images/image_9.png)

## Test cases

Below are some of the test scenarios which I tried and resolved using my Android device.

-	Test Scenario 1
User is able to remove an incorrect character using the clear button “C”.

    Incorrect value entered

    ![](/images/image_10.png)

    Incorrect value corrected

    ![](/images/image_11.png)

-	Test Scenario 2
User is able to reset the entire input.

    Entire input needs to be removed
    
    ![](/images/image_12.png)
    
    Entire output reset to default value

    ![](/images/image_13.png)
    
- Test Scenario 3
User tries to enter a value which is greater than 9 characters (including the decimal point).

    ![](/images/image_14.png)

-	Test Scenario 4
Application is able to provide correct output for the given input.

    ![](/images/image_15.png)

## Reference

> [1] "Download Android Studio and SDK tools  |  Android Developers", Android Developers, 2020. [Online]. Available: https://developer.android.com/studio. [Accessed: 08- Mar- 2020]
.
> [2] "Flowchart Maker & Online Diagram Software", Draw.io, 2020. [Online]. Available: https://www.draw.io/. [Accessed: 08- Mar- 2020]
.
> [3] Kotlin, 2020. [Online]. Available: https://kotlinlang.org/. [Accessed: 08- Mar- 2020]
.
> [4] "HapticFeedbackConstants  |  Android Developers", Android Developers, 2020. [Online]. Available: https://developer.android.com/reference/android/view/HapticFeedbackConstants. [Accessed: 09- Mar- 2020]
.
> [5] "SeekBar  |  Android Developers", Android Developers, 2020. [Online]. Available: https://developer.android.com/reference/android/widget/SeekBar. [Accessed: 09- Mar- 2020]
.
> [6] "Toasts overview  |  Android Developers", Android Developers, 2020. [Online]. Available: https://developer.android.com/guide/topics/ui/notifiers/toasts. [Accessed: 10- Mar- 2020]
.   
> [7] Neilsen, J. (1994). Enhancing the explanatory power of usability heuristic. In: ACM CHI'94 Conf. pp.152-158.