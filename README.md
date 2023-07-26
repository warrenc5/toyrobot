Assistant is a Java developer to help write sections of Java object oriented code to assist with an application that models the simple movement of a toy robot.

please give me the command in maven to create a simple spring boot project in the group au.com.devnull with the artifactId toyrobot and version 1.0.0-SNAPSHOT

please give me the maven spring boot archetype and the spring boot dev tools dependency


please write an enum named Orientation with NORTH, EAST, SOUTH and WEST each with an integer representatian stating from 0.  Add a static fromInt method that returns the Orientation enum. Use java 8 streams to implement the fromInt method.

please write an enum named Command with PLACE, MOVE, LEFT, RIGHT, REPORT

please add a rotateClockwise method to the Orientation enum that will return the Orientation enum represented by adding 1 from the enums int value and taking the modulus of 4.
please add a rotateCounterClockwise method to the Orientation enum that will return the Orientation enum represented by subtracting 1 from the enums int value and taking the modulus of 4.

Please write an enum named Status with MOVING, MISSING

Please write a public class called ToyRobot with a memeber field status of the Status enum type assigned to missing and direction field of the type Direction. 

Add two static member fields DEFAULT_LENGTH and DEFAULT_WIDTH both definitately assigned to 5.
Add two member fields maxLength and maxWidth definately assigned to DEFAULT_LENGTH and DEFAULT_WIDTH respectively.

Also add a main method that takes two arguments and parses them as Integers and passes them to a constructor as length and width and sets the memeber fields maxLength and maxWidth

Add a multi dimensional boolean array member field declaration called table and initialize it in the constructor using those constructor parameters. 

Add two private integer fields called posX and posY definately assign them to 0

Add a method called report the returns the string format of posX, posY and direction separated by commas. If the status field is MISSING return “ROBOT MISSING”

Add a method called place that takes that takes two integers x and y, and one Orientation enum and initializes posX, posY and direction fields respectively. Update the status field to MOVING.

Add a overloaded method called place that takes a string, splits the string using regex on a comma, parses the first two array elements to integers and the third array element to an Orientation enum by name and calles the original place method with these three parameters.

write a method called processCommand that takes an iterator of strings and within a switch statement on next provide a case for all the Command enums, throw IllegalArgumentException on default and show the faulty command in the exception message.

Please write a method in ToyRobot called processCommand with a inputstream parameter that uses scanner to iterate string tokens delimited by spaces. Pass the scanner to the overloaded method as an iterator of strings.

Add a method processCommand that takes a String and passes it as a ByteArrayInputStream to the previous overloaded method 

Add an implementation for the LEFT processCommand statement case that updates the ToyRobot direction field by calling rotateCounterClockwise on the current direction.

Add an implementation for the RIGHT processCommand statement case that updates the ToyRobot direction field by calling rotateClockwise on the current direction.

Add an implementation for the PLACE processCommand enum that passes the iterators next to the place method that takes a string.

Add an implementation for the MOVE processCommand statement case that save the posX and posY into local variables tmpX tmpY and use a nested switch statement for each Orientation case of the direction field so that when the direction is NORTH subtract 1 from posY, When the direction is SOUTH add 1 to posY, When the direction is WEST subtract 1 from posX and when the direction is EAST add 1 to posX.  After the switch statement perform an assignment to true on the table field with the first array index as posX – maxWidth and the second arrayIndex as posY - maxLength. Catch an IndexOutOfBoundsException and prrint “boundary collision” with the current posX,posY and direction and then revert the posX and posY to their original tmpX and tmpY values.

For the processCommand methods LEFT, RIGHT, MOVE case statement check if the status is missing and break to a OUTER label out of the switch statement.

Add an implementation for the REPORT proccessCommand statement case that prints the output of the the report method.

Please make the enums public inner enums and add them at the end of the ToyRobot public class

Please write simple junit test cases to construct the ToyRobot and call process command with the following examples given "PLACE 0,0,NORTH MOVE REPORT" assert report method returns “0,1,NORTH”, given "PLACE 0,0,NORTH LEFT REPORT" assert report method returns “0,0,WEST”, given "PLACE 1,2,EAST MOVE MOVE LEFT MOVE REPORT" assert report method returns “3,3,NORTH” , given the command "MOVE REPORT" then assert report returns “ROBOT MISSING”

Make ToyRobot a spring Singleton Component.

Given that ToyRobot is a spring Singleton Component

Write a spring boot restcontroller class called ToyRobotController that autowire injects the ToyRobot singleton Component.

Add to the controller individual POST resource method mappings for MOVE, LEFT, RIGHT in the Command enum. Call ToyRobot processCommand with the corresponding enum as a string as an argument and return the result of the toy robots report method .

Add to the controller an additional GET resource method mappings for REPORT in the Command enum. Call ToyRobot report method

Add a POST resource method mapping for PLACE enum Command, add RequestParameters to the mapping for integer x, integer y and a Orientation enum called direction and append those as a string format separated by commas to the PLACE command separated with a space call proccessCommand.  And return the result of the toy robots report method .
