#include <string>
#include <vector>

class Highscore {

	public:
		
		Highscore();

		Highscore(int score);//, std::string initials);

		int getScore();
		std::string getInitials();
		int getRank();
		void writeScore(int score, int rank);		
		std::vector<int> readFile();
	
	private:

		int score;
		//std::string initials;
		int rank;
		int findRank(int score);

};
