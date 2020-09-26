using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class HelperBoy : MonoBehaviour {

    public string Text;

    public UnityEngine.UI.Text message;

    private bool show;

    // Use this for initialization
    void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    private void OnTriggerEnter2D(Collider2D collider)
    {
        Character unit = collider.GetComponent<Character>();
        if (unit)
        {
            message.text = Text;
        }
    }

    private void OnTriggerExit2D(Collider2D collider)
    {
        Character unit = collider.GetComponent<Character>();
        if (unit)
        {
            message.text = "";
        }
    }

    /*void OnGUI()
    {
        GUIStyle style = new GUIStyle();
        style.richText = true;
        GUI.Label(new Rect(500, 100, 300, 300), "<size=30> <align=left>" + text + "</size>", style);
    }*/

}
