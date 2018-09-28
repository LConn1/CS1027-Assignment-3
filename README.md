Run the FindShortestPath class and have args[0] be the text file representing the map.
This program will find the shortest path for the drone from the UWO store to the customer’s house if one exists. The program must use a priority queue in order to keep track of what path to take.
There are four types of cells :
  • Free cells. The drone can safely fly across one of these cells to move to an adjacent one.
  • Tower cells. Each one of these cells contains a cellular tower. The drone cannot go through
    these cells or through any cell adjacent to a tower cell.
  • No-flying cells. The drone cannot enter them.
  • Customer cell. This is the destination. The customer cell is a free cell.
