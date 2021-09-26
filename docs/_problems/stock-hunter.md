---
layout: post
title: "Stock Hunter"
---

World has been hit by a pandemic, it is the recession time and market is quite bearish.
The new millennials are on the lookout for the good stocks to fill their portfolios.
Someone tells them to meet the Oracle to search details of the best undervalued stock.
Oracle tells them to visit a cave, which is divided into a grid of squares.
Each square region occupies exactly one coordinate in X,Y format where X and Y are integers and zero or greater.
Cave wall will have clue on - entry point, target point (with best undervalued stock), depth of the Grid, GridKey, Horizontal Stepper, Vertical Stepper.
Each square region of the cave can have Low, Medium or High costs associated with it to access it.
In order to reach to the best undervalued stock, millennials need to do below:

## Task Objective

- Determine the map of the Grid with the smallest rectangle that includes Entry and Target’s coordinates. Represent the Risk Cost as L, M and S for the squares including the Entry and Target
- Find the minimum cost to reach the Target from the Entry point
  - You can only move horizontally or vertically but never diagonally
  - The path of minimum cost can go beyond X and Y coordinates of the target point

Oracle explains that there is a method to determine any square region’s risk cost based on its risk level.

- The risk level of a region can be determined from its risk index. A region’s risk level is = (risk index plus the cave system’s depth)% GridKey.
- The risk index can be determined using the first rule that applies from the list below: The region at 0,0 (the entry of the cave) has a risk index of 0. The region at the coordinates of the target has a risk index of 0. If the region’s Y coordinate is 0, the risk index is its X coordinate times HorizontalStepper. If the region’s X coordinate is 0, the risk index is its Y coordinate times VerticalStepper. Otherwise, the region’s risk index is the result of multiplying the risk levels of the regions at X-1,Y and X,Y-1.
- Risk cost can be calculated based on the risk level
  - If the risk level % 3 is 0, the region’s risk cost is 3, denoted by L
  - If the risk level % 3 is 1, the region’s risk cost is 2, denoted by M
  - If the risk level % 3 is 2, the region’s risk cost is 1, denoted by S

We will do a POST request on your team URL with the endpoint /stock-hunter to evaluate the solution. Input is an array of testcases. Output should be an array of the results of testcases in the same order as the input testcases.

## Sample Input

```json
[
  {
    "entryPoint": {
      "first": 0,
      "second": 0
    },
    "targetPoint": {
      "first": 2,
      "second": 2
    },
    "gridDepth": 156,
    "gridKey": 20183,
    "horizontalStepper": 16807,
    "verticalStepper": 48271
  }
]
```

## Sample Output

```json
[
  {
    "gridMap": [
      ["L", "M", "L"],
      ["L", "L", "L"],
      ["L", "S", "L"]
    ],
    "minimumCost": 9
  }
]
```

## Explanation for sample output

### Grid generation

![Grid Explanation](/CodeItSuisse-2021/assets/images/stock-hunter.png)

Following is the explanation for the grid map values for some of the grid points

- (0,0) --> riskIndex = 0, riskLevel = (0 + 156) % 20183 = 156, (riskLevel % 3) = 0, riskCost = 3 **riskCategory = L**
- (0,1) --> riskIndex = 1\*verticalStepper = 48271, riskLevel = (48271 + 156) % 20183 = 8061, riskLevel % 3 = 0, riskCost = 3 **riskCategory = L**
- (1,0) --> riskIndex = 1\*horizontalStepper = 16807, riskLevel = (16807 + 156) % 20183 = 16963, riskLevel % 3 = (16963 % 3) = 1, riskCost = 2 **riskCategory = M**
- (1,1) --> riskIndex = (riskLevel of (0,1)) \* (riskLevel of (1,0)) = 136738743, riskLevel = (136738743 + 156) % 20183 = 19257, riskLevel % 3 = (19257 % 3) = 0, riskCost = 3 **riskCategory = L**
- (2,2) --> riskIndex = 0, riskLevel = (0 + 156) % 20183 = 156, riskLevel % 3 = 0, riskCost = 3 **riskCategory = L**

### Minimum Cost

Minimum cost path is denoted in bold in the sample output. Minimum cost = riskCost(M) + riskCost(L) + riskCost(S) + riskCost(L) = 2 + 3 + 1 + 3 = 9. Any other path would give total riskCost >= 9

## Scoring

- Answering only "GridMap" correctly gives 20 marks
- Answering only "MinimumCost" gives 80 marks
- Answering both "GridMap" and "MinimumCost" gives 100 marks
- Total score is calculated by adding all marks from individual test cases divided by total number of test cases
