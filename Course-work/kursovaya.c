#include "includesH/book.h"//����������� ����������, ��������� � ���������
#include "includesC/nodes.c" //�������� ��� �������
#include "includesC/enter.c"//���� ������ �� ����� � ������
#include "includesC/print.c"//����������� ����� �������
#include "includesC/sort.c"//����������
#include "includesC/menus.c"//������ ����

int main()
{
    Head *head;

    head = enter();
    if (head)
    {
        printf("There is data from file\n");
        print(head);
        main_menu(head);
        free_list(head);
    }
    return 0;
}



