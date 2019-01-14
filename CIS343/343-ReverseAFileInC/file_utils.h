/**
 * This header file contains the read and write file functions, as stated in the assignment description
 *
 * @author AJ Natzic
 * @date 9-18-2018
 */

/**
* This function reads a file and returns the size of a given file
*
* @param filename  the name of the file
* @param buffer    the buffer used to read the file
* @return  size    the size of the file
*/
int read_file( char* filename, char **buffer );

/**
* This function writes to a file
*
* @param filename  the name of the file
* @param buffer    the buffer used to write to the file
* @param size          the size of the file
* @return  0
*/
int write_file( char* filename, char *buffer, int size);

