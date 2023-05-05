$(document).ready(function() {
    // cacher le menu déroulant au début
    $(".index-header .dropdown-menu").hide();

    // afficher le menu déroulant lorsque le bouton est cliqué
    $(".index-header .dropdown-toggle").click(function() {
        $(".index-header .dropdown-menu").toggle();
    });

    // fermer le menu déroulant lorsqu'un élément est cliqué
    $(".index-header .dropdown-menu a").click(function() {
        $(".index-header .dropdown-menu").hide();
    });
});