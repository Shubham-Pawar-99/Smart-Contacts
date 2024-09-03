console.log("Script loaded...");

let currentTheme = getTheme();

// Apply the initial theme when the script loads
changePageTheme(currentTheme, null);

changeTheme();

function changeTheme() {
    const changeThemeButton = document.querySelector("#theme_change_button");

    changeThemeButton.addEventListener("click", (e) => {
        console.log("change theme button clicked.....");

        const oldTheme = currentTheme;

        if (currentTheme === "dark") {
            currentTheme = "light";
        } else {
            currentTheme = "dark";
        }

        changePageTheme(currentTheme, oldTheme);
    });
}

// Set theme to localStorage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

// Get theme from localStorage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}

// Change the current page theme
function changePageTheme(theme, oldTheme) {
    // Update local storage
    setTheme(theme);

    // Remove the old theme if it exists
    if (oldTheme) {
        document.querySelector("html").classList.remove(oldTheme);
    }

    // Set the new theme
    document.querySelector("html").classList.add(theme);

    // Change the text of the button
    document.querySelector("#theme_change_button").querySelector("span").textContent = theme === "light" ? "dark" : "light";
}
