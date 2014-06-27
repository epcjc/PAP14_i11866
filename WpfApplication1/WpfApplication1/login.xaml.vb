Imports System.IO
Imports MySql.Data.MySqlClient
Imports System.Data

Public Class login

    Function getSHA1Hash(ByVal strToHash As String) As String

        Dim sha1Obj As New Security.Cryptography.SHA1CryptoServiceProvider
        Dim bytesToHash() As Byte = System.Text.Encoding.ASCII.GetBytes(strToHash)

        bytesToHash = sha1Obj.ComputeHash(bytesToHash)

        Dim strResult As String = ""

        For Each b As Byte In bytesToHash
            strResult += b.ToString("x2")
        Next

        Return strResult

    End Function

    Private Sub Button_Click(sender As Object, e As RoutedEventArgs)
        End

    End Sub

    Private Sub Grid_MouseLeftButtonDown(sender As Object, e As MouseButtonEventArgs)
        Me.DragMove()
    End Sub

    'Represents an SQL statement or stored procedure to execute against a data source.
    Dim cmd As New MySqlCommand
    Dim da As New MySqlDataAdapter
    'declare conn as connection and it will now a new connection because 
    'it is equal to Getconnection Function
    Dim con As MySqlConnection = jokenconn()

    Public Function jokenconn() As MySqlConnection
        Return New MySqlConnection("Data Source=projetos.epcjc.net;Database=i11866;User ID=i11866;Password=DretNed7;")
    End Function

    Private Sub entrar_Click(sender As Object, e As RoutedEventArgs) Handles entrar.Click
        Dim sql As String
        Dim publictable As New DataTable
        Dim pw As String

        Try
            'check if the textbox is equal to nothing then it will display the message below!.
            If txtuname.Text = "" And txtpass.Password = "" Then
                MsgBox("Preencha os campos")

            Else
                pw = getSHA1Hash(txtpass.Password)
                sql = "SELECT * FROM users WHERE username ='" & txtuname.Text & "' and password = '" & pw & "'"
                'bind the connection and query
                With cmd
                    .Connection = con
                    .CommandText = sql
                End With
                da.SelectCommand = cmd
                da.Fill(publictable)
                'check if theres a result by getting the count number of rows
                If publictable.Rows.Count > 0 Then

                    'reset all the two textbox
                    txtuname.Text = ""
                    txtpass.Password = ""
                    Dim frm As MainWindow = New MainWindow
                    frm.Show()

                    Me.Close()

                Else
                    MsgBox("Contacte o administrador para registar no sistema!")
                    txtuname.Text = ""
                    txtpass.Password = ""
                End If

                da.Dispose()
            End If

        Catch ex As Exception
            MsgBox(ex.Message)

        End Try
        con.Clone()
    End Sub
End Class
