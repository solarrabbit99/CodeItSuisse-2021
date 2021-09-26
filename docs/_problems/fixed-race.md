---
layout: post
title: "Fixed Race"
---

Every hour, there will be a swimming tournament with N number of participants

The speed of the swimmers are fixed and they will swim the same timing every single race.

Every minute, there will be a race of **10** swimmers chosen from all the participants.

The faster the swimmer, the higher the chance of them participating in races.

You are to deduce the rankings of the swimmers in each race by making use of results from previous attempts in the hour.

Score will be awarded for every position guessed correctly.

For a race, the sample input is given below.

## Sample Input

Input is a **String** of swimmers delimited by comma

Rudolf Ravelo,Joseph Jarosz,Amos Alward,Britt Bisceglia,Nelson Noss,Shona Stanek,Alysia Alejandro,Cortez Carranco,Caitlin Cully,Justin Jack

## Sample Expected Output

Expected output is a **String** of swimmers delimited by comma from the fastest swimmer to the slowest.

If you guess that Joseph Jarosz to be first, Caitlin Cully to be second, Nelson Noss to be third and so on, your output should look something like this.

Joseph Jarosz,Caitlin Cully,Nelson Noss,Cortez Carranco,Shona Stanek,Rudolf Ravelo,Alysia Alejandro,Justin Jack,Britt Bisceglia,Amos Alward

After you have made your guess, we will update your group's dashboard.

In this case, you will get 20% of total score if 2 positions are guessed correctly

Your guess is Joseph Jarosz, Caitlin Cully, Nelson Noss, Cortez Carranco, Shona Stanek, Rudolf Ravelo, Alysia Alejandro, Justin Jack, Britt Bisceglia, Amos Alward. Winner of this race is Justin Jack. Your score is 100.0

## Requirements

Expose 1 POST endpoint /fixedrace for evaluation
