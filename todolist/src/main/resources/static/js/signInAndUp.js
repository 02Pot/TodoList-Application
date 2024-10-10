document.addEventListener('DOMContentLoaded', () => {
    const signUpButton = document.getElementById('signUpButton');
    const signInButton = document.getElementById('signInButton');
    const signInForm = document.getElementById('signIn');
    const signUpForm = document.getElementById('signup');
    const submitButton = document.querySelector('input[type="submit"][name="signUp"]');
    const loginButton = document.querySelector('input[type="submit"][name="signIn"]');

    signUpButton.addEventListener('click', function(){
        signInForm.style.display = "none";
        signUpForm.style.display = "block";
    });

    signInButton.addEventListener('click', function(){
        signInForm.style.display = "block";
        signUpForm.style.display = "none";
    });

    submitButton.addEventListener('click', (event) => {
        event.preventDefault();

        const userFirstName = document.getElementById('fName').value;
        const userLastName = document.getElementById('lName').value;
        const userEmail = document.getElementById('reg_email').value;
        const userPassword = document.getElementById('reg_password').value;
        const data = {
            userFirstName,
            userLastName,
            userEmail,
            userPassword
        };

        const jsonData = JSON.stringify(data);
        fetch('/page/u_register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: jsonData
        
        })
        .then(response => {
            // if(response.ok)
            //     console.log(data);
            // else{
            //     console.error('Error', response.statusText);
            // }
        })
    });


    // loginButton.addEventListener('click', (event) => {
    //     event.preventDefault();

    //     const userEmail = document.getElementById('log_email').value;
    //     const userPassword = document.getElementById('log_password').value;
    //     const loginData = {
    //         userEmail,
    //         userPassword
    //     };

    //     const loginJsonData = JSON.stringify(loginData);
    //     fetch('/page/u_login', {
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/json',
    //         },
    //         body: loginJsonData
    //     })
    //     .then(response => {
    //     });
    // });
});
