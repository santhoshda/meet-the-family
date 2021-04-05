# Meet the family
Solution for the Geektrust's coding challenge **Meet the family**.

https://www.geektrust.in/coding-problem/backend/family

### How to build this project?
```
mvn clean install -DskipTests
```

### How to run this project?
```
java -jar <path_to>/geektrust.jar <absolute_path_to_input_file>
```

### Example Input File
```
/meet-the-family/src/test/resources/TestFile.txt
```

### Accepted Commands
1. ADD_CHILD Mother’s-Name Child's-Name Gender
2. GET_RELATIONSHIP Name Relationship

### Conditions
1. Names should be single word and should not be repeated.
2. Children can only be added to Females.

#### Possible Genders
* Male
* Female

#### Possible Relationships
* Paternal-Uncle
* Maternal-Uncle
* Paternal-Aunt
* Maternal-Aunt
* Sister-In-Law
* Brother-In-Law
* Son
* Daughter
* Siblings

