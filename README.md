# Project PDL : OpenCompare Form

## Objective
  The purpose of this project is to generate, from a product comparison matrices (PCM), a form to add an entry (ie, a product). Rather than filling the cells of an array to create a new product, we propose to use a form. 	
  This project is an additional feature for opencompare.org to help a community of users to import, edit, visualize and exploit product comparison matrices (PCM).

## Result
* The project allows to users to generate a form from one file PCM or generate all forms from all PCM files present in the project.
  All forms are generated to HTML files, in the same folder where the PCM file is located.
* In the form : 
	- If the type field is other than text (ie: Date, Integer,...) and if you want write text, you can use a button to switch into a text field. 
	- You can choose the text field other to add other than a checkbox, if you check and you complete the field text other. 
	- It's the same for the partial type field.

## License
Free use, free reproduction and free distribution.
	
## Technology used
* Java
* Junit
* FreeMarker
* JavaScript
* Bootstrap

## Development tools
* [Eclipse](https://eclipse.org/)
* [Maven](https://maven.apache.org)

## Architecture
* The project contains all Java classes to treat a PCM, generate the HTML code of a form of a PCM.
* There are of course Junit tests to test methods of each Java classes.
* The project uses also a Html template FreMarker to generate easily a HTML code.
* In the template HTML, we intagrated a Bootstrap to get a web responsive design.
* Moreover, in the HTML code, there is JavaScript code to generate for each text field.

## Getting started
You can download the Maven project or clone the repertory :
	
    git clone https://github.com/Ophelle/PDL
    
In Eclipse or others Java IDE, to run the application, you have just to run The Main class in the folder src/main/java and the org.opencompare package.

When the application is running, you can tap "1" to generate one form, "2" to generate alls available forms or "q" to quit the application.

If you tap "1", just tap the path file of a PCM file. For developpers, you can also after tap "1", tap "d" to generate a form from a default PCM defined by a properties file, in the properties folder of the Maven project.
