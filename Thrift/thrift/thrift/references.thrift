namespace java com.kedacom.thrift.gen

const bool flag = true

struct UserStruct {
    1: i64 id
    2: string name
    3: double score
}

service UserService{
    UserStruct getStruct(1: i64 id)
}