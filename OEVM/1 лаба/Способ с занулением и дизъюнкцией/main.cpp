#include <iostream>
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

   for (i=sizeof(ldc)-1; i>=0; --i)
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
   doubleUn a1; longUn a2;
   a1.number = 15.37;
   a2.number = 7098;
   print_double(a1);
   print_long(a2);

   int right;
   int index, length, count, i, j=0;
   long result = 0;

   do
   {cout << "What direction do you want to move?\n[0] left\n[1] right\nEnter: ";
   cin >> right;} while (right!=0 && right!=1);
   do
   {cout << "Enter the index of high digit[0..31]: ";
   cin >> index;} while (index<0 && index>sizeof(a2)*4-1);
   do
   {cout << "Enter number of required bits: ";
   cin >> length;}while (length<0 && length>sizeof(a2)*4-index);
   do
   {cout << "Enter number of shifting: "; //������ �����
   cin >> count;} while(count<0);
   //right = 0; index = 22; length=8; count=3;

   int size = sizeof(a2)*8; //���-�� ����� � �����
   result = a2.number;
   result >>= size-(index+length);//������� ��� �������� ����� ������
   result %= 1<<length;//������ ���� �����, ������� ����
   cout << "This is srez: "<<result<<endl;
   if (right) result >>=count;
   else result <<=count; //���������� � ������ �������
   cout << "This is sdvinutyy srez: "<<result<<"  11101110000"<<endl;
   result %= (1<<length);//��� ��� ������ ���� �����
   cout <<"This is itogovyy srez: "<< result<<"  01110000"<<endl;
   for (i=size-(index+length); i<size-index; i++) a2.number &= ~(1<<i);
   result <<= (size -(index+length));
   length = size-index;
   cout <<"This is vstavlyaemyy srez: "<<result<<"  0111000000"<<endl;
   a2.number |= result;
   print_long(a2);
   return 0;
}
