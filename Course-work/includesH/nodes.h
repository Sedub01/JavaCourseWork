Head *make_head();//������������� ������
Node *create_node2(char *name,char *author, int year, int lists, float wordcount,float average); //�������� ���� � ����������
void add_first2(Head *my_head, Node *new_node);//���������� ������ � ������� ���� ������
void insert_after2(Head *my_head,Node *current_node, Node *new_node);//�������
Node *fill_node(FILE *myfile);//���������� ���� �����������
int add_node(Head *head);//���������� ����� �� ��������� �������
void free_list(Head *head);//������� ������
