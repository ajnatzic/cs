#include <iostream>
#include <sstream>
#include <fstream>
#include <string>
#include <vector>
#include "Highscore.h"

/**********************************************************************
*Default Constructor
**********************************************************************/
Highscore::Highscore(){
	score = 0;
	//initials = "";
	rank = 0;
}

/**********************************************************************
*Constructor takes in a score
**********************************************************************/
Highscore::Highscore(int score){  //,std::string initials)	
	this->score = score;
	//this->initials = initials;
	this->rank = findRank(score);
	writeScore(score,rank);

}
/**********************************************************************
*returns the rank
**********************************************************************/
int Highscore::getRank(){
	return rank;
}


/**********************************************************************
*Take in all the numbers in a file to a vector
**********************************************************************/
std::vector<int> Highscore::readFile(){
	std::string line;
	std::vector<int> scoreList;
	std::ifstream file ("Highscores.txt");
	int temp;
	
	while(getline(file,line)){
		std::istringstream(line) >> temp;
		scoreList.push_back(temp);
	}
	
	file.close();

	return scoreList;	
}

/**********************************************************************
*Put all the scores in a vector into a file, but insert the new
*	highscore into its correct position
**********************************************************************/
void Highscore::writeScore(int score, int rank){
	
	std::vector<int> scoreList = readFile();
	
	//delete contents of old highscore
	std::remove("Highscores.txt");

	std::ofstream file("Highscores.txt");

	//re-paste in all the old highscores
	for(int i=1;i<scoreList.size()+1;i++){

		//slide new highscore in place		
		if(i == rank){
			file <<  score << "\n";
		}

		file << scoreList.at(i-1) << "\n";
	}
	if(rank > scoreList.size()){
		file << score << "\n";
	}

	file.close();
}


/**********************************************************************
*Calculates the rank of a number compared to the list in Highscores.txt
**********************************************************************/
int Highscore::findRank(int score){

	//open "Highscores.txt"
	std::string line;
	std::ifstream file ("Highscores.txt");
	
	/*//file doesn't exist yet
	if(!file.good()){
		file.close();
		std::ofstream makeFile("Highscores.txt");
		makeFile.close();
		std::ifstream file("Highscores.txt");
	}*/
	
	int count = 0;

	//no highscores yet
	if(file.eof()){
		return 1;
	}

	//The file is available
	if (file.is_open()){

		//there are more lines	
		while (getline(file,line)){
			int oldScore = 0;
			count++;			

			//compare score to the current line's value
			std::istringstream (line) >> oldScore;
			if(score > oldScore){
				return count;
			}

		}
			
		//end of file reached: last place
		count++;
		
		file.close();

	}else{
		std::cout << "FILE ERROR" << std::endl;
	}
	
	return count;
}

/**********************************************************************
*Test for findRank, example: "./a.out 3200"
*Outputs the scores it compares to, then prints the rank.
**********************************************************************/
int main(int argc, char** argv){
	int score;
	std::istringstream (argv[1]) >> score;
	Highscore h(score);
	//std::cout << h.getRank() << std::endl;
}	
