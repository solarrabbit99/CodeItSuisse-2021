# CodeIt Suisse Challenge 2021 (Krankenwagen)

This repository contains our solution for the Credit Suisse Online Coding Challenge 2021. Our team
consists of Lee Zheng Han and Siew Hui Zhuan.

## Running Locally

Make sure you have Java and Maven installed. Also, install the [Heroku CLI](https://cli.heroku.com/).

```sh
$ git clone https://github.com/zhenghanlee/CodeItSuisse-2021.git
$ cd CodeItSuisse-2021
$ mvn install
$ heroku local:start
```

Your app should now be running on [localhost:5000](http://localhost:5000/).

## Deploying to Heroku

```sh
$ heroku create
$ git push heroku main
$ heroku open
```
