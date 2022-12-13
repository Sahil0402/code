#include <iostream>
#include <fstream>
#include <vector>
#include <string>
using namespace std;

char buff[40];
char M[200][4];
char R[4];
char IR[4];

int IC;
int C;

int SI = 0;
string line;

void initialise()
{
    IC = 0;
    C = 0;

    cout << "Initialising all the memories" << endl;
    for (int i = 0; i < 40; i++)
    {
        buff[i] = '0';
    }
    for (int i = 0; i < 200; i++)
    {
        for (int j = 0; j < 4; j++)
        {
            M[i][j] = '0';
        }
    }
    for (int i = 0; i < 4; i++)
    {
        IR[i] = '0';
        R[i] = '0';
    }
}

void load(string line, int count)
{
    if (count == 1)
    {
        int lenline=line.length();
        for (int i = 0; i < lenline; i++)
        {
            buff[i] = line[i];
        }

        //moving the instructions from buffer to the memory
        cout << "Moving data from buffer to memory: " << endl
             << "Block 0:" << endl;

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                do
                {
                    M[i][j] = buff[4 * i + j];
                    cout << M[i][j];
                    break;
                } while (buff[4 * i + j] != 'H');
            }
            cout << endl;
        }
        cout << "\nProgram Block executed.." << endl << endl;
        cout << "Memory Display starts here." << endl << endl;
    }

    else if (count == 3)
    {
        for (int i = 0; i < 40; i++)
        {
            buff[i] = '0';
        }
        for (int i = 0; i < line.length(); i++)
        {
            buff[i] = line[i];
        }
    }
}

void Read()
{
    IR[3] = '0';
    string line1;
    
    for (int i = 0; i < 4; i++)
    {
        line1[i] = IR[i];
    }

    int num = int(IR[2] - '0') * 10;
    int a = 0;
    for (int i = num; i < num + 10; i++)
    {
        for (int j = 0; j < 4; j++)
        {
            do
            {
                M[i][j] = buff[4 * a + j];
                break;
            } while (buff[4 * a + j] != '0');
        }
        a++;
    }

    for (int i = 0; i < 200; i++)
    {
        if (i % 10 == 0)
        {
            cout << "BLOCK NUMBER: " << i / 10 << endl;
        }
        for (int j = 0; j < 4; j++)
        {
            cout << M[i][j];
        }
        cout << endl;
    }
}
void arithmetic(){
    int num1,num2;
    string operand;
    cout<<"Enter the number 1"<<endl;
    cin>>num1;
    cout<<"Enter the number 2"<<endl;
    cin>>num2;
    cout<<"Enter the operand"<<endl;
    cin>>operand;
    if(operand=="+"){
        cout<<"Output is:"<<num1+num2<<endl;
    }
    else if(operand=="-"){
        cout<<"Output is:"<<num1-num2<<endl;
    }
    else if(operand=="/"){
        cout<<"Output is:"<<num1/num2<<endl;
    }
    else if(operand=="*"){
        cout<<"Output is:"<<num1*num2<<endl;
    }
    else if(operand=="%"){
        cout<<"Output is:"<<num1%num2<<endl;
    }
}

void masterMode()
{
    switch (SI)
    {
    case 1:
        Read();
        break;
    case 2:
        // Write(); for PD
        break;
    case 3:
        // lR();
        break;
    case 4:
        // SR();
        break;
    case 5:
        // CR();
        break;
    case 6:
        // BT();
        break;
    case 7:
        // Terminate();
        break;
    case 8:
        arithmetic();
        break;

    default:
        cout << "Enter your choice again!";
        break;
    }
    SI = 0;//initial
}

void startExecution()
{
    cout << "Execution starts.." << endl;
    while (true)
    {
        if (IC == 200)//full memory
        {
            break;
        }
        
        IR[0] = M[IC][0];
        IR[1] = M[IC][1];
        IR[2] = M[IC][2];
        IR[3] = M[IC][3];

        IC++;

        if (IR[0] == 'G' && IR[1] == 'D')
        {
            SI = 1;//read
            masterMode();
        }

        else if (IR[0] == 'P' && IR[1] == 'D')
        {
            SI = 2;
            masterMode();
        }
        else if (IR[0] == 'L' && IR[1] == 'R')
        {
            SI = 3;
            masterMode();
        }
        else if (IR[0] == 'S' && IR[1] == 'R')
        {
            SI = 4;
            masterMode();
        }
        else if (IR[0] == 'C' && IR[1] == 'R')
        {
            SI = 5;
            masterMode();
        }
        else if (IR[0] == 'B' && IR[1] == 'T')
        {
            SI = 6;
            masterMode();
        }
        else if (IR[0] == 'A' && IR[1] == 'R')
        {
            SI = 8;
            masterMode();
        }

        else if (IR[0] == 'H')
        {
            SI = 7;//halt
            masterMode();
        }
    }
}

void readFile()
{
    ifstream file;
    file.open("input.txt");
    
    char ch;
    string s;
    int count = 0;
    while (getline(file, line))//line by line
    {
        if (line[0] == '$')
        {
            for (int i = 1; i < 4; i++)
            {
                s[i - 1] = line[i];//getting the data into the string
            }

            if (s[0] == 'A' && s[1] == 'M' && s[2] == 'J')
            {
                initialise();
                count = 0;
            }
            else if (s[0] == 'D' && s[1] == 'T' && s[2] == 'A')
            {
                for (int i = 0; i < line.length(); i++)
                {
                    buff[i] = '0';
                }
                cout << endl;
                count = 2;
            }
            else if (s[0] == 'E' && s[1] == 'N' && s[2] == 'D')
            {
                //end of the program
            }

            count++;
            continue;
        }

        if (count == 1)
        {
            load(line, count);
        }
        if (count == 3)//as we have incremented early
        {
            load(line, count);
            startExecution();
            cout << endl;
        }
    }
    file.close();
}

int main()
{
    readFile();
    return 0;
}