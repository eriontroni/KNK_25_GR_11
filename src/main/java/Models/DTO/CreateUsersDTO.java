package Models.DTO;

public class CreateUsersDTO {

// CREATE TABLE Users (
//    id SERIAL PRIMARY KEY,
//    username VARCHAR(100) UNIQUE NOT NULL,
//    email VARCHAR(255) UNIQUE NOT NULL,
//    password_hash TEXT NOT NULL,
//    role VARCHAR(50) CHECK (role IN ('Admin', 'Receptionist', 'Customer')) NOT NULL
//);

    public class CreateUserDTO {

        private String username;
        private String email;
        private String password_hash;
        private String role;

        public CreateUserDTO(String username, String email, String password_hash, String role) {
            this.username = username;
            this.email = email;
            this.password_hash = password_hash;
            this.role = role;
        }


        public String getUsername() { return username;}

        public void setUsername(String username) { this.username = username; }

        public String getEmail() { return email; }

        public void setEmail(String email) { this.email = email; }

        public String getPassword_hash() { return password_hash; }

        public void setPassword_hash(String password_hash) { this.password_hash = password_hash; }

        public String getRole() { return role; }

        public void setRole(String role) { this.role = role; }
    }
}
