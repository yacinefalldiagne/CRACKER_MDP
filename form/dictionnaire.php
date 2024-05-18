<?php $title = 'Attaque par dictionnaire'; ?>
<?php $nav = 'dictionnaire'; ?>
<?php require('header.php');?>
<body>
<?php
    if(!isset($_POST["login"]) && !isset($_POST["password"])){
?>
    <div class="container">
        <h2>Attaque par dictionnaire</h2>
        <div class="row justify-content-center align-items-center" style="height: 50vh;">
            <div class="col-md-6">
                <form action="dictionnaire.php" method="POST" onsubmit="return validation()">
                    <div class="form-group row">
                        <label for="login" class="col-sm-2 col-form-label">Login</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="login" value="" name="login" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="password" class="col-sm-2 col-form-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password" placeholder="Password" name="password">
                        </div>
                    </div>
                    <div style="padding : 10px;" class="text-center">
                        <button type="submit" class="btn btn-primary">Se connecter</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
    function validation() {

        var loginId = document.getElementById('login');
        var login = loginId.value;
        var alphanumericRegex = /^[a-zA-Z0-9]+$/;

        if (!alphanumericRegex.test(login)) {
            alert("Le login doit être constitué de caractères alphanumériques.");
            return false;
        }

        var passwordId = document.getElementById('password');
        var password = passwordId.value;

        if (password.length > 8) {
            alert("Le mot de passe doit contenir au maximum 8 caractères alphanumériques.");
            return false;
        }

        return true;
    }
    </script>
<?php
    }else {
        $login = htmlspecialchars($_POST['login']); 
        $password = htmlspecialchars($_POST['password']); 

        if(($login == "admin") && ($password == "123456")){
            http_response_code($response_code = 202);
            echo "Connexion réussie <br>";
            // var_dump(http_response_code());
            echo "<br>";
            echo "Le code de retour est : " . $response_code;
        }else{
            echo "Connexion échouée";
            http_response_code($response_code = 401);
            // var_dump(http_response_code());
            echo "<br>";
            echo "Le code de retour est : " . $response_code;
        }
    }
?>
</body>
