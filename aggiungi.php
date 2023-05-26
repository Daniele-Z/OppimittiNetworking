<?php
  // Connessione al database MySQL
  $conn = mysqli_connect('localhost', 'root', '', 'sqd');
  if (!$conn) {
    die('Connessione al database fallita: ' . mysqli_connect_error());
  }

  // Ottieni i valori inviati tramite il formulario di aggiunta
  $id = $_POST['id'];
  $nome = $_POST['Nome'];
  $cognome = $_POST['Cognome'];
  $sesso = $_POST['Sesso'];
  $nascita = $_POST['Nascita'];
  $stipendio = $_POST['Stipendio'];
  $impiego = $_POST['Impiego'];
  $orario = $_POST['OrarioInserimento'];

  // Esegui la query per aggiungere il dipendente nel database
  $query = "INSERT INTO dipendenti (Nome, Cognome, Sesso, Nascita, Stipendio, Impiego, OrarioInserimento) VALUES ('$nome', '$cognome', '$sesso', '$nascita', '$stipendio', '$impiego', '$orario')";
  $result = mysqli_query($conn, $query);

  if ($result) {
    echo "Dipendente aggiunto con successo!";
    header("Refresh:0; url=http://localhost/Dipendenti/dipendenti.php");
  } else {
    echo "Errore durante l'aggiunta del dipendente.";
  }

  // Chiudi la connessione al database
  mysqli_close($conn);
?>