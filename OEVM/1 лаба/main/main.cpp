#include <iostream>
#include <cmath>
using namespace std;

/*
� ����������� �� ������ �������� �������
����������� �������� ����� � ����������
��������� ����� ������ � �������� ��
������ �� ���������� ������������� �
�������� ������� ���������.

8 ������� - long ( =signed long int ) � double

��������� ����������� ����� � ��������
������������� ������� �� ��������
���������� �������� � ��������
����������� ������ ��������,
���������� ������� � ����� ��������
������� � ������ �������� � ����������.
1 ��� �� ����, 11 ��� �� �������, 52 �� �������� - double
*/

union doubleUn //8 ������
{
   double number;
   char arr[sizeof(number)];//����.������ � !!!������!!!
};

union longUn // 4 �����
{
   long number;
   char arr[sizeof(number)];
};

void print_double( doubleUn ldc )
{
   unsigned char c;
   int i, j;

   for ( i = sizeof( ldc ) - 1; i >= 0; --i )
   {
      c = ldc.arr[i]; //8 ������ �������� �����
      //����� � ������ ����������� � 2 ��
      for ( j = 0; j < 8; ++j )
         if (c & (128>>j)) cout<<"1";
         else cout<<"0";
         cout<<" ";
   }
   cout << "= " << ldc.number << endl;
}

void print_long( longUn ldc )
{
   unsigned char c;
   int i, j;

   for ( i = sizeof( ldc ) - 1; i >= 0; --i )
   {
      c = ldc.arr[i]; //4 ������� �������� �����
      //����� � ������ ����������� � 2 ��
      for ( j = 0; j < 8; ++j )
         if (c & (128>>j)) cout<<"1";
         else cout<<"0";
      cout<<" ";
   }
   cout << "= " << ldc.number << endl;
}

int main()
{
   doubleUn a1;
   a1.number = 15.375;
   print_double(a1);

   longUn a2;
   a2.number = 7098;
   print_long(a2);

   bool right;
   int index, length, count, i, j=0, k;
   long result = 0;
   /*cout << "What direction do you want to move?\n[0] left\n[1] right\nEnter: ";
   cin >> right;
   cout << "Enter the index of high digit[0..31]: ";
   cin >> index;
   cout << "Enter number of required bits: ";
   cin >> length;
   cout << "Enter number of shifting: ";
   cin >> count;*/
   right = 0; index = 22; length=8; count=3;

   int size = sizeof(a2)*8;
   result = a2.number;
   result >>= size-(index+length);
   result = result%(long)pow(2, length);
   cout << "This is srez: "<<result<<endl;
   if (right) result >>=count;
   else result <<=count;
   cout << "This is sdvinutyy srez: "<<result<<"  11101110000"<<endl;
   result %= (long)pow(2, length);
   cout <<"This is itogovyy srez: "<< result<<"  1110000"<<endl;
   //� = ~(a^b); // ����. ���������������

   //a2.number= ~( (a2.number << (size -(index+length)) ) ^ result);
   result <<= (size -(index+length));
   cout <<"This is vstavlyaemyy srez: "<<result<<"  111000000"<<endl;
   length = size -index;
   for (i=0; i<length; i++)
   {
      if (!(result>>i)&1) a2.number &= ((result<<i)&1);
      else if (((result>>i)&1)) a2.number |= ((result<<i)&1);
   }
   print_long(a2);

   return 0;
}
