export default function AttendanceBook(props){
    // 1. 샘플 데이터
    const students = [
        {id : 1 , name : 'Inje'},
        {id : 2 , name : 'Steve'},
        {id : 3 , name : 'Bill'},
        {id : 4 , name : 'Jeff'}
    ];

    return(
        <ul>
            {students.map((student)=>{
                return <li key={student.id}>{student.name}</li>
            })}
        </ul>
    );
}