**File notes**
screen-field notes help with screen rotation.

Colors and string xml files under (Monster\app\src\main\res\values) are missing cases. my acccachments are a start,

but the Monster setup will not run without them.

*****strings.xml****** is the file that needs to be finished, as I completed color.

Fill in the correct code for the strings under strings.xml and that part *should* work.

Also, for the Monster code, the Monsters when clicked do not lose lives. 

DotView contains the working grid that updates within Dotview. This is a 3x3 grid. 

Doc2 is a screenshot of it working, with green and yellow fills to show that all 9 boxes 

are there.

--4x4 may be implemented later. Restricted to 3x3 or 4x4 because user MUST be able to click 
box easily, as per instructions. 

--I could not get the spawning of the initial monsters working correctly yet.
*******************************************************************************************

Possible pseudocode/format for combining the different aspects of the program.

*******PLEASE READ*******

I believe we should use an central array repository for holding the Monsters. 
if you check my main.java under the AndroidStudioProjects\cs313f16p5-project-5\jk_update
folder, I use an Object [][][][][][][] array Monsters. 

This could contain the monster ID (to better call or sort them), the x,y,coordinates,
the number of lives, the color, the period/refresh rate, and creation time.

--refresh is how many ticks must pass for the monster to move or change color. 

--creation time contains the time of when the monster was created, using 

System.CurrentTimeMillis();. comparing that to the  System.CurrentTimeMillis();

for the current tick number (let's call that long currTick) as follows:

(currTick-startTime)%refresh

gives you the if check needed to see if a monster can move/change color. 

if the check results in 0, either move or change color, or both.


Just apply to a foreach monster in Monsters loop, and you have most of the 

monster/screen refresh code.


MonsterGrid is a visio to help better show the flow from what I saw thus far.
worst case, split the setup into 2 threads.


Any comments would be appreciated.