include "references.thrift"

namespace java com.kedacom.thrift.gen

enum Number {
  One = 1,
  Two = 2,
  Three = 3
}

exception CustomException {
  1: i32 id,
  2: string name,
  3: optional string description
}

const map<string,string> hello = {'hello':'world'}

service DemoService extends references.UserService {
    string throwException(1:bool flag) throws (1:CustomException error),
    string helloWorld()
}