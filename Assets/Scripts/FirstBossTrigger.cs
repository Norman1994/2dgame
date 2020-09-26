using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FirstBossTrigger : MonoBehaviour {

	
	void Start ()
    {
	}
	
	void Update ()
    {
	}

    private void OnTriggerEnter2D(Collider2D collision)
    {
        Unit unit = collision.GetComponent<Unit>();

        if (unit && unit is Character)
        {
            FirstBoss.move = true;
            Destroy(gameObject);
        }
    }

}
