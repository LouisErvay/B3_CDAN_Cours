#include <iostream>
#include <memory>

using namespace std;


int main() {
    auto ptr1 = make_shared<int>(100);

    cout << ptr1.use_count() << endl;

    auto ptr2 = ptr1;

    cout << ptr1.use_count() << endl;

    return 0;
}

