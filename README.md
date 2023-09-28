# Java practical test assignment
## Table of Contents

---
- Project requirements
- Build

## Project requirements
It has the following fields:
<ol>
  <li> Email (required). Add validation against email pattern </li>
  <li> First name (required) </li>
  <li> Last name (required) </li>
  <li> Birth date (required). Value must be earlier than current date </li>
  <li> Address (optional) </li>
  <li> Phone number (optional) </li>
</ol>
It has the following functionality:
<ol> 
  <li> Create user. It allows to register users who are more than [18] years old. The value [18] should be taken from properties file. </li>
  <li> Update one/some user fields </li>
  <li> Update all user fields </li>
  <li> Delete user </li>
  <li> Search for users by birth date range. Add the validation which checks that “From” is less than “To”.  Should return a list of objects </li>
</ol>

## Build

---
```bash
cd path/to/project/

./mvnw clean install
./mvnw compile
```
