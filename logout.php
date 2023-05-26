<?php
// Avvia la sessione
session_start();

// Distruggi tutte le variabili di sessione
session_destroy();

// Reindirizza l'utente alla pagina di login o ad altre azioni desiderate
header("Location: logoutht.html");
exit;
?>