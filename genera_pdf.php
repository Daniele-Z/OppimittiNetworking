<?php
require_once('C:\Users\User\OneDrive\Desktop\tcpdf\tcpdf.php');

function generaPDF() {
    // Crea un nuovo documento PDF
    $pdf = new TCPDF('P', 'mm', 'A4', true, 'UTF-8');

    // Imposta le informazioni del documento
    $pdf->SetCreator('Nome del Creatore');
    $pdf->SetAuthor('Nome dell\'Autore');
    $pdf->SetTitle('Titolo del Documento');
    $pdf->SetSubject('Soggetto del Documento');
    $pdf->SetKeywords('keyword1, keyword2, keyword3');

    // Aggiungi una pagina
    $pdf->AddPage();

    // Imposta il font
    $pdf->SetFont('helvetica', '', 12);

    // Connetti al database (sostituisci con le tue informazioni di connessione)
    $servername = 'localhost';
    $username = 'root';
    $password = '';
    $dbname = 'dipendenti';

    $conn = new mysqli($servername, $username, $password, $dbname);
    if ($conn->connect_error) {
        die('Connessione fallita: ' . $conn->connect_error);
    }

    // Esegui la query per recuperare i dati dal database (sostituisci con la tua query)
    $query = "SELECT * FROM dipendenti";
    $result = $conn->query($query);

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            $pdf->Cell(0, 10, 'Nome: ' . $row['Nome'], 0, 1);
            $pdf->Cell(0, 10, 'Cognome: ' . $row['Cognome'], 0, 1);
            $pdf->Cell(0, 10, 'Sesso: ' . $row['Sesso'], 0, 1);
            $pdf->Ln(10); // Aggiungi una riga vuota
        }
    } else {
        $pdf->Cell(0, 10, 'Nessun dato trovato nel database.', 0, 1);
    }

    // Chiudi la connessione al database
    $conn->close();

    // Genera il PDF
    $pdf->Output('output.pdf', 'F');
}
?>