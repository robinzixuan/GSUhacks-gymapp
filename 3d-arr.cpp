#include <iostream>
#include <vector>
#include <string>
#include <iterator>
#include<iterator>
#include <algorithm>
using std::ostream_iterator;
using namespace std;
int main()
{
    int *** arr;
    int i,j,k;
    string value,tp,time1;
    int size1=1000000;
    int size2=5;
    int size3=24;
    vector<string> gym;
    vector<string> type{"leg","chest","back","shoulder","belly"};
    vector<string> time{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};

    arr = new int**[size1];
    for (i = 0; i < size1; i ++) {
        arr[i] = new int*[size2];
        for (j = 0; j < size2; j ++) {
            arr[i][j] = new int[size3];
        }
    }
    /////////////////// 用for循环初始化
    for (i = 0; i < size1; i ++) {
        for (j = 0; j < size2; j ++) {
            for (k = 0; k < size3; k ++) {
                arr[i][j][k] = 0;
            }
        }
    }
    vector<string>::iterator it;
    it= find(gym.begin(),gym.end(),value);
    if (it!=gym.end()){
        int d1=distance(gym.begin(), it);
    }else{
        gym.push_back(value);
    }
    vector<string>::iterator it1;
    it1= find(type.begin(),type.end(),tp);
    int d2=distance(type.begin(), it1);
    vector<string>::iterator it2;
    it2= find(time.begin(),time.end(),time1);
    int d3=distance(time.begin(), it2);
    
    
    

    
    ///////////////////// 释放内存
    for (i = 0; i < size1; i ++) {
        for (j = 0; j < size2; j ++) {
            delete[] arr[i][j];
        }
        delete[] arr[i];
    }
    delete[] arr;
    return 0;
}
