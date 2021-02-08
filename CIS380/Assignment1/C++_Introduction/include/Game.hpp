//COMPLETE ME
#ifndef             __HPP__GAME__
#define             __HPP__GAME__

#include <string>

class Game {

    public:
        Game();
        ~Game();

        void setName(std::string name);
        std::string getName();

        void setOnline(bool online);
        bool getOnline();

        void setSales(double sales);
        double getSales();

        void setConsole(std::string console);
        std::string getConsole();

        void setRating(char rate);
        char getRating();

        void setReleaseYear(int year);
        int getReleaseYear();  
       

    private:
        std::string name;
        bool online;
        double sales;
        std::string console;
        char rating;
        int year;
};

#endif