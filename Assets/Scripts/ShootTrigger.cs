using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ShootTrigger : MonoBehaviour
{
    ShootableMonster shoot;
    // Use this for initialization
    void Start()
    {
        shoot = new ShootableMonster();
    }

    // Update is called once per frame
    void Update()
    {

    }

    protected void OnTriggerEnter2D(Collider2D collider)
    {
        Character unit = collider.GetComponent<Character>();
        if (unit)
        {
        }
    }
}
