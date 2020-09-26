using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FrogMessage : MonoBehaviour
{

    private string Text;

    private bool show;

    // Use this for initialization
    void Start()
    {

    }

    // Update is called once per frame
    void Update()
    {

    }

    private void OnTriggerEnter2D(Collider2D collider)
    {
        Character unit = collider.GetComponent<Character>();
        show = true;
        Text = "Брат, я скучаю по Wildfly! Мы не можем больше кодить на ASP.NET, мы запутались в строках подключения в appsettings.json. Ква-ква :(";
    }

    private void OnTriggerExit2D(Collider2D collider)
    {
        show = false;
        Character unit = collider.GetComponent<Character>();
        if (unit && show == false)
        {
            Text = "";
        }
    }

    void OnGUI()
    {
        GUIStyle style = new GUIStyle();
        style.richText = true;
        GUI.Label(new Rect(10, 100, 300, 300), "<size=20>" + Text + "</size>", style);
    }
}
