#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char **argv)
{
	char   cmd[1024];
   if (argc >= 2)
   	strcpy(cmd, argv[1]);
   else
   	return -1;

   for (int i = 2 ; i < argc ; i++)
		strcat(cmd, argv[i]);

   system(cmd);
}
