package Models.DTO;

public class CreateUsersDTO {

// CREATE TABLE Users (
//    id SERIAL PRIMARY KEY,
//    username VARCHAR(100) UNIQUE NOT NULL,
//    email VARCHAR(255) UNIQUE NOT NULL,
//    password_hash TEXT NOT NULL,
//    role VARCHAR(50) CHECK (role IN ('Admin', 'Receptionist', 'Customer')) NOT NULL
//);

        private String username;
        private String email;
        private String password_hash;
        private String salted_hash;

        public CreateUsersDTO(String username, String email, String password_hash, String salted_hash) {
            this.username = username;
            this.email = email;
            this.password_hash = password_hash;
            this.salted_hash = salted_hash;
        }


        public String getUsername() { return username;}

        public void setUsername(String username) { this.username = username; }

        public String getEmail() { return email; }

        public void setEmail(String email) { this.email = email; }

        public String getPassword_hash() { return password_hash; }

        public void setPassword_hash(String password_hash) { this.password_hash = password_hash; }

        public String getSalted_hash() {
            return salted_hash;
        }

        public void setSalted_hash(String salted_hash) {
            this.salted_hash = salted_hash;
        }
}
