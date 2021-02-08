// COMPETE ME
#include <vector>
#include <iostream>
#include <string>

#include "Game.hpp"

Game::Game(){
    std::string name = "";
    bool online = false;
    double sales = 0.0;
    std::string console = "";
    char rating = '\0';
    int year = 0;
}

Game::~Game(){

}

void Game::setName(std::string name){
    this->name = name;
}
std::string Game::getName(){
    return this->name;
}


void Game::setOnline(bool online){
    this->online = online;
}
bool Game::getOnline(){
    return this->online;
}

void Game::setSales(double sales){
    this->sales = sales;
}
double Game::getSales(){
    return this->sales;
}

void Game::setConsole(std::string console){
    this->console = console;
}
std::string Game::getConsole(){
    return this->console;
}

void Game::setRating(char rating){
    this->rating = rating;
}
char Game::getRating(){
    return this->rating;
}

void Game::setReleaseYear(int year){
    this->year = year;
}
int Game::getReleaseYear(){
    return this->year;
}

