# Missionaries and Cannibals Problem Solver

This repository contains a program that solves the classic Missionaries and Cannibals problem using both Breadth-First Search (BFS) and Depth-First Search (DFS) algorithms. The problem involves three missionaries and three cannibals who must cross a river using a boat that can carry at most two people. The constraints are such that the missionaries on either side of the river should never be outnumbered by cannibals, or they will be eaten!

## Problem Description

The problem can be represented as a state-space search problem. The program in this repository uses both BFS and DFS algorithms to find a sequence of moves that satisfies the problem constraints.

## Breadth-First Search (BFS)

Breadth-First Search is an algorithm for traversing or searching tree or graph data structures. It explores all the vertices at the current depth before moving on to vertices at the next depth level. In the context of the Missionaries and Cannibals problem, BFS systematically explores all possible states of the problem in breadth-wise fashion, ensuring that the solution found is the shortest path.

<div align="center">
  <img src="https://github.com/BaselAbuHamed/Missionaries_and_Cannibals_Problem/assets/107325485/9b30641f-6b5a-4c7b-961b-39ab29e6cb69" alt="BFS Algorithm" width="500"/>
</div>

## Depth-First Search (DFS)

Depth-First Search is another algorithm for traversing or searching tree or graph data structures. It explores as far as possible along each branch before backtracking. In the Missionaries and Cannibals problem, DFS explores as deeply as possible along each branch of the state-space tree. While it may not guarantee the shortest path, it can be memory-efficient.

<div align="center">
  <img src="https://github.com/BaselAbuHamed/Missionaries_and_Cannibals_Problem/assets/107325485/5b7adedd-e406-44c9-83b3-9d88cccbb585" alt="DFS Algorithm" width="500"/>
</div>
